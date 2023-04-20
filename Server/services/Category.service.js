import CategoryRepository from '../repositories/Category.repository.js';
import { v2 as cloudinary } from 'cloudinary';
import { CloudinaryStorage } from 'multer-storage-cloudinary';
const CategoryServices = {
  async changeIsDisable(id, is_disable) {
    return await CategoryRepository.changeIsDisable(id, is_disable);
  },
  async create(name, image) {
    if (name == null || name == '' || image == '') {
      cloudinary.uploader.destroy(image.filename); // ham nay kiểm tra nếu name bằng null thì xoá file trên cloudiary.Phải có nha quý zị
      return false;
    } else {
      return await CategoryRepository.create(name, image.path);
    }
  },
  async update(id, name) {
    return await CategoryRepository.update(id, name);
  },
  async delete(id) {
    return await CategoryRepository.delete(id);
  },
  async findOne(id) {
    return await CategoryRepository.findOneByID(id);
  },
  async findAll() {
    return await CategoryRepository.findAll();
  },
  // Cần method gì thì tự implements !!
};
export default CategoryServices;
