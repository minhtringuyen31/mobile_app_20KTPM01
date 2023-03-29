import CategoryRepository from "../repositories/Category.repository.js"
const CategoryServices = {
    // Đừng thắc mắc tại sao không gọi trực tiếp từ Respository --> Respository chỉ nơi CRUD database, còn services mình tự implement tuy thuộc vào yêu cầu !! 
    async create(name) {
        return await CategoryRepository.create(name)
    },
    async update(id, name) {
        return await CategoryRepository.update(id, name)
    },
    async delete(id) {
        return await CategoryRepository.delete(id);
    },
    async findOne(id) {
        return await CategoryRepository.findOneByID(id);
    },
    async findAll() {
        return await CategoryRepository.findAll();
    }
    // Cần method gì thì tự implements !! 



}
export default CategoryServices;