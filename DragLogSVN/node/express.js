var express = require('express');
var bodyParser = require('body-parser');
var morgan  = require('morgan');

var mysql = require('mysql');

var app = express();
app.use(morgan('short')); // Logging middleware
app.use(bodyParser());	// body parsing middleware.
app.use(bodyParser.json({ type: 'application/vnd.api+json' }));

var dbconn;
var db_config = {
	host : 'localhost',
	user : 'semi',
	password : 'semi',
	database:'semidb'
};
// connection 에러로 서버 다운 방지
var handleDisconnect = function() {
	dbconn = mysql.createConnection(db_config);
	
	dbconn.connect(function(err) {
		if(err) {
			console.log('error when connecting to db:', err);
			setTimeout(handleDisconnect(), 2000);
		}
	});
	
	dbconn.on('error', function(err) {
		console.log('db error', err);
		if(err.code === 'PROTOCOL_CONNECTION_LOST') {
			handleDisconnect();
		} else {
			throw err;
		}
	});
};
handleDisconnect();



//test
app.post('/test', function(req,res){
	res.header("Access-Control-Allow-Origin", "*");
	res.header("Access-Control-Allow-Headers", "X-Requested-With");
	
	console.log(req.body);
	res.send(req.body);
});

// SNS 공유
app.post('/snsShare', function(req,res){
	res.header("Access-Control-Allow-Origin", "*");
	res.header("Access-Control-Allow-Headers", "X-Requested-With");
	
	console.log(req.body);
	var urlParam = req.body.urlParam;
	var scope = req.body.scope;
	var period = req.body.period;
	var result = req.body.result;
	
	var snsSql = 'INSERT IGNORE INTO SNS_LOGS (URL_PARAM, SCOPE, PERIOD, TITLE, COLOR, VAL) VALUES ("'
		+urlParam+'", "'+scope+'", "'+period+'", "'+result[0].title+'", "'+result[0].color+'", '+result[0].value+')';
	
	if (result.length > 1) {
		for (var i=1; i<result.length; i++) {
			snsSql += ', ("'+urlParam+'", "'+scope+'", "'+period+'", "'+result[i].title+'", "'+result[i].color+'", '+result[i].value+')';
		}
	}
	console.log(snsSql);
	
	dbconn.query(snsSql, function(err, rows){
		if (err) {
			console.log(err);
            throw err;
		}
		console.log(rows);
		res.send(rows.insertId.toString());
	});
});


// 다른 사람의 어제 기록 보기
app.post('/other', function(req,res){
	res.header("Access-Control-Allow-Origin", "*");
	res.header("Access-Control-Allow-Headers", "X-Requested-With");
	var me = req.body;
	console.log(me);
	
	if (me.EMAIL == '' || me.GENDER == '' || me.AGE == '' || me.JOB == '' 
		|| me.SALARY == '' || me.SPEND == '' || me.SCHOLAR == '' || me.MARRY == '') {
		res.send('0signup');
	} else {
		var usersSql = 'SELECT l.USER_NO FROM LOG AS l'
			+ ' INNER JOIN USER AS u ON l.USER_NO = u.USER_NO'
			+ ' WHERE (l.USER_NO != '+me.USER_NO+')' 
			+ ' AND ('
			+ ' (GENDER != '+me.GENDER+' AND AGE>='+me.AGE+'-3 AND AGE<='+me.AGE+'+3 AND JOB='+me.JOB+')'
			+ ' OR (GENDER != '+me.GENDER+' AND AGE>='+me.AGE+'-3 AND AGE<='+me.AGE+'+3 AND SALARY='+me.SALARY+')'
			+ ' OR (GENDER != '+me.GENDER+' AND AGE>='+me.AGE+'-3 AND AGE<='+me.AGE+'+3 AND SCHOLAR='+me.SCHOLAR+')'
			+ ' OR (GENDER != '+me.GENDER+' AND JOB='+me.JOB+' AND SALARY='+me.SALARY+')'
			+ ' OR (GENDER != '+me.GENDER+' AND JOB='+me.JOB+' AND SCHOLAR='+me.SCHOLAR+')'
			+ ' OR (GENDER != '+me.GENDER+' AND SALARY='+me.SALARY+' AND SCHOLAR='+me.SCHOLAR+')'
			
			+ ' OR (GENDER = '+me.GENDER+' AND AGE>='+me.AGE+'-3 AND AGE<='+me.AGE+'+3)'
			+ ' OR (GENDER = '+me.GENDER+' AND JOB='+me.JOB+')'
			+ ' OR (GENDER = '+me.GENDER+' AND SALARY='+me.SALARY+')'
			+ ' OR (GENDER = '+me.GENDER+' AND SCHOLAR='+me.SCHOLAR+')'
			+ ' )'
			+ ' AND (START_TIME < CURDATE()) ';
		dbconn.query(usersSql ,function(err, rows) {
			if (err) { console.log(err); throw err; }
			if (rows.length == 0) { res.send('0users'); } 
			else {
				var users = [];
				for (var i=0; i<rows.length; i++) {
					if (users.indexOf(rows[i].USER_NO) === -1) users.push(rows[i].USER_NO);
				}
				var ran = Math.floor(Math.random() * users.length);
				var randomNo = users[ran];
				console.log('---- Random user :' + randomNo);
				
				dbconn.query('SELECT * FROM USER WHERE (USER_NO = ?)', randomNo
						,function(err, rows) {
					if (err) { console.log(err); throw err; }
					delete rows[0].EMAIL;
					delete rows[0].PASSWORD;
					
					var data = [];
					data.push(rows[0]);
					dbconn.query('SELECT * FROM LOG WHERE (USER_NO = ?) AND START_TIME>= '
							+ ' (SELECT DATE(START_TIME) AS lastSt FROM LOG WHERE (USER_NO = ?) '
							+ ' ORDER BY lastSt DESC LIMIT 1)', [randomNo, randomNo]
					,function(err, rows) {
						if (err) { console.log(err); throw err; }
						if (rows.length == 0) { res.send('0log'); }
						else {
							data.push(rows);
							res.send(data);
						}
					});
				});
			}
		});
	}
});

// 장비에서 서버로 기록 보내기
app.post('/submitLog', function(req, res) {
	res.header("Access-Control-Allow-Origin", "*");
	res.header("Access-Control-Allow-Headers", "X-Requested-With");
	
	var logList = req.body.logList;
	if (logList != undefined) {
		var logSQL = 'INSERT IGNORE INTO LOG (USER_NO, ACTION, START_TIME, END_TIME, DURATION) ' 
			+ ' VALUES ('+logList[0].USER_NO+',"'+logList[0].ACTION+'","'+logList[0].START_TIME+'","'+logList[0].END_TIME+'",'+logList[0].DURATION+')';
		
		for (var i = 1; i < logList.length; i++) {
			logSQL += ',\n ('+logList[i].USER_NO+',"'+logList[i].ACTION+'","'+logList[i].START_TIME+'","'+logList[i].END_TIME+'",'+logList[i].DURATION+')';
		}
		
		dbconn.query(logSQL, function(err, rows){
			if (err) { console.log(err); throw err; }
			
			console.log(rows);
			res.send(rows);
		});
		
	}
	
});


// 장비에서 최초 실행시 USER ID 생성 후 전달
app.get('/createUser',function(req,res){
	res.header("Access-Control-Allow-Origin", "*");
	res.header("Access-Control-Allow-Headers", "X-Requested-With");
	dbconn.query('INSERT INTO USER SET PASSWORD="" ', function(err, rows){
		if (err) {
			console.log(err);
            throw err;
		}
		console.log(rows);
		res.send(rows.insertId.toString());
	});
});


// 회원가입
app.post('/signup',function(req,res){
	res.header("Access-Control-Allow-Origin", "*");
	res.header("Access-Control-Allow-Headers", "X-Requested-With");
	var user = req.body;
	
	console.log(user);
	dbconn.query('UPDATE USER SET EMAIL=?, PASSWORD=?, AGE=?, GENDER=?, JOB=?, '
			+' SALARY=?, SPEND=?, SCHOLAR=?, MARRY=? WHERE USER_NO=?',
    		[user.email, user.password, user.age, user.gender, user.job, user.salary, user.spend, user.scholar, user.marry, user.user_no], 
    		function(err, rows, fields){
    		if (err) {
        		console.log(err);
            throw err;
    		} else {
        		res.send('ok');
    		}
	});
});


// 로그인
app.post('/login', function(req,res){
	res.header("Access-Control-Allow-Origin", "*");
	res.header("Access-Control-Allow-Headers", "X-Requested-With");
	
	var email = req.body.email;
	var password = req.body.password;
	
	dbconn.query('SELECT * FROM USER WHERE EMAIL = ?', email, function(err, rows, fields){
		if (err) {
			console.log(err);
            throw err;
		}
		else {
			if (rows[0].PASSWORD == password) {
				//res.cookie('email', req.body.email);
				var user = rows[0];
				console.log(user);
				user.PASSWORD = '';
				
				res.send(user);
			} else {
				res.send('no');
			}
		}
	});
});

//이메일 중복 확인
app.post('/emailCheck',function(req,res){
	if (req.body.email != '') {
		res.setHeader('Access-Control-Allow-Origin', '*');
		var email = req.body.email;
		dbconn.query('SELECT * FROM USER WHERE EMAIL = ?', email, function(err, rows){
			if (err) {console.log(err);}
			if (rows.length) { 
				res.send('already'); 
			} 
			else { 
				res.send('empty'); 
			}
		});
	}
});


// (테스트 용) pathname 없으면 user list 출력
app.get('/', function(req,res){
	
	dbconn.query('SELECT * FROM USER', function(err, rows, fields){
		if (err) {
			console.log('Query err');
			console.log(err);
		}
		//console.log(rows);
		//console.log(fields);
		res.json(rows);
	});
});

app.listen(1111);
console.log('Server ON : 1111');