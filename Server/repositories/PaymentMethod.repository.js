import DB from '../configs/db.js';

const PaymentMethodRepository = {
  async findOne(id) {
    const query = `SELECT * FROM payment_method WHERE id = ?`;
    try {
      const [result] = await DB.pool().query(query, [id]);
      return result[0];
    } catch (error) {
      console.log(error);
      return false;
    }
  },
};

export default PaymentMethodRepository;
