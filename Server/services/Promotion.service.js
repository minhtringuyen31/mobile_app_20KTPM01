import PromotionRepository from '../repositories/Promotion.repository.js';
import { v2 as cloudinary } from 'cloudinary';
const PromotionServices = {
  async countPromotion() {
    return await PromotionRepository.countPromotion();
  },
  async changeIsDisable(id, isDisable) {
    return await PromotionRepository.changeIsDisable(id, isDisable);
  },
  async create(
    name,
    description,
    discount,
    start_date,
    end_date,
    quantity,
    code,
    file
  ) {
    if (
      name == null ||
      name == '' ||
      description == null ||
      description == '' ||
      discount == null ||
      discount == '' ||
      start_date == null ||
      start_date == '' ||
      end_date == null ||
      end_date == '' ||
      quantity == null ||
      quantity == '' ||
      code == null ||
      code == '' ||
      file == ''
    ) {
      cloudinary.uploader.destroy(file.filename);
      return false;
    } else {
      return await PromotionRepository.create(
        name,
        description,
        discount,
        start_date,
        end_date,
        file.path,
        quantity,
        code
      );
    }
  },
  async updateWithoutImage(
    id,
    name,
    description,
    discount,
    start_date,
    end_date,
    quantity,
    code
  ) {
    return await PromotionRepository.updateWithoutImage(
      id,
      name,
      description,
      discount,
      start_date,
      end_date,
      quantity,
      code
    );
  },
  async update(
    id,
    name,
    description,
    discount,
    start_date,
    end_date,
    image,
    quantity,
    code
  ) {
    if (
      name == null ||
      name == '' ||
      description == null ||
      description == '' ||
      discount == null ||
      discount == '' ||
      start_date == null ||
      start_date == '' ||
      end_date == null ||
      end_date == '' ||
      quantity == null ||
      quantity == '' ||
      code == null ||
      code == '' ||
      image == ''
    ) {
      cloudinary.uploader.destroy(image.filename);
      return false;
    } else {
      return await PromotionRepository.update(
        id,
        name,
        description,
        discount,
        start_date,
        end_date,
        image.path,
        quantity,
        code
      );
    }
  },
  async delete(id) {
    return await PromotionRepository.delete(id);
  },
  async findOne(id) {
    return await PromotionRepository.findOneByID(id);
  },
  async findAll() {
    return await PromotionRepository.findAll();
  },
  // Cần method gì thì tự implements !!
};

export default PromotionServices;
