import ToppingRepository from '../repositories/Topping.repository.js';
const ToppingServices = {
  async create(category_id, name, price, checked) {
    return await ToppingRepository.create(category_id, name, price, checked);
  },
  async update(id, category_id, name, price, checked) {
    return await ToppingRepository.update(
      id,
      category_id,
      name,
      price,
      checked
    );
  },
  async delete(id) {
    return await ToppingRepository.delete(id);
  },
  async findOne(id) {
    return await ToppingRepository.findOneByID(id);
  },
  async findAll() {
    return await ToppingRepository.findAll();
  },
  // Cần method gì thì tự implements !!
};

export default ToppingServices;
