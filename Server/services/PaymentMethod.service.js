import PaymentMethodRepository from '../repositories/PaymentMethod.repository.js';

const PaymentMethodServices = {
  async findOne(id) {
    return await PaymentMethodRepository.findOne(id);
  },
};

export default PaymentMethodServices;
