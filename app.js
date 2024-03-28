
const express = require('express');
const bodyParser = require('body-parser');

const app = express();

app.use(bodyParser.urlencoded({extended: false}));

app.use('/add-flight', (req, res, next) => {
    res.send('<form action="/flight" method="POST"><input type="text" name="destination"/><button type="submit">Submit</button></form>');
})
app.use('/flight', (req, res, next) => {
    console.log(req.body)
    res.redirect('/');
})

app.use((req, res, next) => {
    // console.log(req)
    res.send('<h1>Hello from the Airport Managment System server!</h1>');
})

app.listen(3000);