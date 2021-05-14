// 写测试的接口
const express = require('express')
const app = express()
const mysql = require('mysql')

// app.all('*', function (req, res, next) {
//     res.header("Access-Control-Allow-Origin", "*");
//     res.header("Access-Control-Allow-Headers", "X-Requested-With");
//     res.header("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
//     res.header("X-Powered-By", ' 3.2.1')
//     res.header("Content-Type", "application/json;charset=utf-8");
//     next();
// });

app.listen(3000, () => {
    console.log('服务器打开了')
})

// 自定义跨域中间件
var allowCors = function (req, res, next) {
    res.header('Access-Control-Allow-Origin', req.headers.origin);
    res.header('Access-Control-Allow-Methods', 'GET,PUT,POST,DELETE,OPTIONS');
    res.header('Access-Control-Allow-Headers', 'Content-Type');
    res.header('Access-Control-Allow-Credentials', 'true');
    next();
};
app.use(allowCors);//使用跨域中间件

// 建立数据库的连接
const db = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '123456',
    database: 'hbkjdx'
})

db.connect((err) => {
    if (err) throw err
    console.log('连接成功')
})

// 获取
app.get("/ab", (req, res) => {
    let sql = "select * from comment"
    db.query(sql, (err, result) => {
        if (err) {
            console.log('失败了' + err)
        } else {
            console.log(result)
            // res.send('查询成功')
            res.json(result)
        }
    })
})


//报名加1
// 获取
app.get("/ja", (req, res) => {
    //获取到课程id
    console.log(req.query)
    let id = req.query.cid
    // let sql = "UPDATE Person SET enroll =enroll +1 WHERE id = id "
    let sql = `UPDATE course SET enroll =enroll +1 WHERE id = '${id}' `
    // let sql = `UPDATE course SET enroll =enroll +1 WHERE id = 'CTHKUyUy' `
    db.query(sql, (err, result) => {
        if (err) {
            console.log('失败了' + err)
        } else {
            console.log(result)
            // res.send('查询成功')
            res.json(result)
        }
    })
})