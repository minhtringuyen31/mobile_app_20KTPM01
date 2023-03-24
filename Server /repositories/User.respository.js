import db from "./configs/db.js"
const User = {
    create: (req) => {
        const query = `
        INSERT INTO mydb.users
            (id, fullname, gender, dayOfBirth, password, email, phone, address, isDisable, avatar, role)
        VALUES
            (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        `;
    },
    update: (req) => {

    },
    delete: (req) => {

    },
    read: (req) => {

    },



}

export default User;