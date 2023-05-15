import UserRepository from '../repositories/User.respository.js';
const UserServices = {
    async changeIsDisable(id, is_disable) {
        return await UserRepository.changeIsDisable(id, is_disable);
    },
    async create(
        name,
        gender,
        email,
        phone,
        password,
        date_of_birth,
        address,
        avatar,
        role,
        is_disable,
       
    ) {
        return await UserRepository.create(
            name,
            gender,
            email,
            phone,
            password,
            date_of_birth,
            address,
            avatar,
            role,
            is_disable,
            
        );
    },
    async update(
        id,
        name,
        gender,
        date_of_birth,
        password,
        email,
        phone,
        address,
        is_disable,
        avatar,
        role
    ) {
        return await UserRepository.update(
            id,
            name,
            gender,
            date_of_birth,
            password,
            email,
            phone,
            address,
            is_disable,
            avatar,
            role
        );
    },
    async delete(id) {
        return await UserRepository.delete(id);
    },
    async findOne(id) {
        return await UserRepository.findOneByID(id);
    },
    async findAll() {
        return await UserRepository.findAll();
    },
    // Cần method gì thì tự implements !!
    //
    async signup(email, password) {
        return await UserRepository.signup(email, password);
    },
    async findEmail(email) {
        return await UserRepository.findOneByEmail(email);
    },
    async findPhone(phone) {
        return await UserRepository.findOneByPhone(phone);
    },
    async setOTP(email,otp){
        return await UserRepository.setOTP(email,otp);

    },
    async setPass(email){
        return await UserRepository.setPass(email);

    },
    async getPass(email){
        return await UserRepository.getPass(email);

    },
    async checkOTP(email,otp){
        return await UserRepository.checkOTP(email,otp);
    }
    ,
    async changepass(id, password) {
        return await UserRepository.changepass(id, password);
    },
    async editprofile(id, name, email, gender, date_of_birth, address) {
        return await UserRepository.editprofile(
            id,
            name,
            email,
            gender,
            date_of_birth,
            address
        );
    },
};

export default UserServices;
