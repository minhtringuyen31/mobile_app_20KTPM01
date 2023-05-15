import ProductRepository from '../repositories/Product.repository.js';
import { v2 as cloudinary } from 'cloudinary';
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
    image,
    status,
    category_id,
    update_date,
    release_date,
    sales
  ) {
    if (
      name == null ||
      name == '' ||
      description == null ||
      description == '' ||
      size == null ||
      size == '' ||
      price_S == null ||
      price_S == '' ||
      price_M == null ||
      price_M == '' ||
      price_L == null ||
      price_L == '' ||
      image == '' ||
      status == null ||
      status == '' ||
      category_id == null ||
      category_id == '' ||
      update_date == null ||
      update_date == '' ||
      release_date == null ||
      release_date == '' ||
      sales == null ||
      sales == ''
    ) {
      cloudinary.uploader.destroy(image.filename);
    } else {
      return await ProductRepository.create(
        name,
        description,
        size,
        price_S,
        price_M,
        price_L,
        image.path,
        status,
        category_id,
        update_date,
        release_date,
        sales
      );
    }
  },
  async updateWithoutImage(
    id,
    name,
    description,
    size,
    price_S,
    price_M,
    price_L,
    category_id,
    update_date
  ) {
    return await ProductRepository.updateWithoutImage(
      id,
      name,
      description,
      size,
      price_S,
      price_M,
      price_L,
      category_id,
      update_date
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
    category_id,
    update_date
  ) {
    if (
      name == null ||
      name == '' ||
      description == null ||
      description == '' ||
      size == null ||
      size == '' ||
      price_S == null ||
      price_S == '' ||
      price_M == null ||
      price_M == '' ||
      price_L == null ||
      price_L == '' ||
      image == '' ||
      category_id == null ||
      category_id == '' ||
      update_date == null ||
      update_date == ''
    ) {
      cloudinary.uploader.destroy(image.filename);
    } else {
      return await ProductRepository.update(
        id,
        name,
        description,
        size,
        price_S,
        price_M,
        price_L,
        image.path,
        category_id,
        update_date
      );
    }
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
    return await ProductRepository.findAllFavProduct(userId);
  },
  async addNewFavProduct(userId, productId) {
    return await ProductRepository.addNewFavProduct(userId, productId);
  },
  async removeFavProduct(userId, productId) {
    return await ProductRepository.removeFavProduct(userId, productId);
  },
  async isExistedFavProduct(userId, productId) {
    return await ProductRepository.isExistedFavProduct(userId, productId);
  },
  // Cần method gì thì tự implements !!
};
export default ProductServices;
