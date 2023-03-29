import UserRepository from "../repositories/User.respository.js"
const UserServices = {
    // Đừng thắc mắc tại sao không gọi trực tiếp từ Respository --> Respository chỉ nơi CRUD database, còn services mình tự implement tuy thuộc vào yêu cầu !! 
    async create(name, gender, date_of_birth, password, email, phone, address, is_disable, avatar, role) {
        return await UserRepository.create(name, gender, date_of_birth, password, email, phone, address, is_disable, avatar, role)
    },
    async update(id, name, gender, date_of_birth, password, email, phone, address, is_disable, avatar, role) {
        return await UserRepository.update(id, name, gender, date_of_birth, password, email, phone, address, is_disable, avatar, role)
    },
    async delete(id) {
        return await UserRepository.delete(id);
    },
    async findOne(id) {
        return await UserRepository.findOneByID(id);
    },
    async findAll() {
        return  await UserRepository.findAll();
    }
    // Cần method gì thì tự implements !! 



}

export default UserServices;