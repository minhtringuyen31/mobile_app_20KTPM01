import express from "express";
import db from "./configs/db.js"
import UserRoute from './routes/User.route.js'
const app = express()
const port = 3000
db();
app.use('/api/users', UserRoute)
app.listen(port, () => {
    console.log(`Example app listening on port ${port}`)
})