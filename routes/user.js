const express = require('express');

const router = express.Router();

router.get('/flights', (req, res, next) => {
    res.send('<h1>This is a list of the available flights.</h1>');
})

module.exports = router;