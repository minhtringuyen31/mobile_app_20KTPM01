import PromotionRepository from "../repositories/Promotion.repository.js"
const PromotionServices = {
    async changeIsDisable(id, isDisable) {
        return await PromotionRepository.changeIsDisable(id, isDisable);
    },
    // Đừng thắc mắc tại sao không gọi trực tiếp từ Respository --> Respository chỉ nơi CRUD database, còn services mình tự implement tuy thuộc vào yêu cầu !! 
    async create(name, description, discount, start_date, end_date, image, quantity, code) {
        return await PromotionRepository.create(name, description, discount, start_date, end_date, image, quantity, code)
    },
    async update(id, name, description, discount, start_date, end_date, image, quantity, code) {
        return await PromotionRepository.update(id, name, description, discount, start_date, end_date, image, quantity, code)
    },
    async delete(id) {
        return await PromotionRepository.delete(id);
    },
    async findOne(id) {
        return await PromotionRepository.findOneByID(id);
    },
    async findAll() {
        return await PromotionRepository.findAll();
    }
    // Cần method gì thì tự implements !! 



}

export default PromotionServices;