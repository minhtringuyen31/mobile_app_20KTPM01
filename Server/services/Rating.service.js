import RatingRepository from "../repositories/Rating.repository.js"
const RatingServices = {
    // Đừng thắc mắc tại sao không gọi trực tiếp từ Respository --> Respository chỉ nơi CRUD database, còn services mình tự implement tuy thuộc vào yêu cầu !! 
    async create(user_id, user_name, user_image, product_id, score, comment) {
        return await RatingRepository.create(user_id, user_name, user_image, product_id, score, comment)
    },
    async update(id, user_id, product_id, score, comment, create_at) {
        return await RatingRepository.update(id, user_id, product_id, score, comment, create_at)
    },
    async delete(id) {
        return await RatingRepository.delete(id);
    },
    async findRating(id) {
        return await RatingRepository.findRatingByProductID(id);
    },
    async findAll() {
        return await RatingRepository.findAll();
    }
    // Cần method gì thì tự implements !! 



}

export default RatingServices;