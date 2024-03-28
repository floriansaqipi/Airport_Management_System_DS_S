const express = require('express');

const router = express.Router();

router.get('/add-flight', (req, res, next) => {
    res.send(`<form action="/admin/add-flight" method="POST">
                <input type="text" name="destination"/>
                <button type="submit">Submit</button>
            </form>`);
})

router.post('/add-flight', (req, res, next) => {
    console.log(req.body);
    res.redirect('/');
})

module.exports = router;