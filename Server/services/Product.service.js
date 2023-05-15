import ProductRepository from '../repositories/Product.repository.js';
const ProductServices = {
  async changeStatus(id, status) {
    return await ProductRepository.changeStatus(id, status);
  },
  async changeIsDisable(id, is_disable) {
    return await ProductRepository.changeIsDisable(id, is_disable);
  },
  async create(
    name,
    description,
    size,
    price_S,
    price_M,
    price_L,
    note,
    image,
    status,
    category_id,
    update_date,
    release_date,
    sales
  ) {
    return await ProductRepository.create(
      name,
      description,
      size,
      price_S,
      price_M,
      price_L,
      note,
      image,
      status,
      category_id,
      update_date,
      release_date,
      sales
    );
  },
  async update(
    id,
    name,
    description,
    size,
    price_S,
    price_M,
    price_L,
    image,
    status,
    category_id,
    update_date,
    release_date,
    sales
  ) {
    return await ProductRepository.update(
      id,
      name,
      description,
      size,
      price_S,
      price_M,
      price_L,
      image,
      status,
      category_id,
      update_date,
      release_date,
      sales
    );
  },
  async delete(id) {
    return await ProductRepository.delete(id);
  },
  async findOne(id) {
    return await ProductRepository.findOneByID(id);
  },
  async findAll() {
    return await ProductRepository.findAll();
  },
  async findAllFavProduct(userId) {
    return await ProductRepository.findAllFavProduct(userId)
  },
  async addNewFavProduct(userId, productId) {
    return await ProductRepository.addNewFavProduct(userId, productId)
  },
  async removeFavProduct(userId, productId) {
    return await ProductRepository.removeFavProduct(userId, productId)
  },
  async isExistedFavProduct(userId, productId) {
    return await ProductRepository.isExistedFavProduct(userId, productId)
  },
  async getRating(productId) {
    return await ProductRepository.getRating(productId)
  }
  // Cần method gì thì tự implements !!
};
export default ProductServices;
