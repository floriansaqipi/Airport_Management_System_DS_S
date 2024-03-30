
const express = require('express');
const bodyParser = require('body-parser');

const adminRoutes = require('./routes/admin');
const userRoutes = require('./routes/user');
const airportRoutes = require('./routes/airport');

const app = express();

app.use(bodyParser.urlencoded({extended: false}));

app.use('/admin', adminRoutes);
app.use(userRoutes)
app.use(airportRoutes)

app.listen(3000);