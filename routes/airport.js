const express = require('express');

const router = express.Router();

router.get('/', (req, res, next) => {
    res.send('<h1>Hello from the Airport Managment System server!</h1>');
})

router.use((req, res, next) => {
    res.send('<h1>404 Page not found!</h1>')
})

module.exports = router;