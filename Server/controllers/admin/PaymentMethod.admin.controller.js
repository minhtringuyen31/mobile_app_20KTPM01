import PaymentMethodServices from '../../services/PaymentMethod.service.js';

const PaymentMethodAdminController = {
  findOne: async (req, res) => {
    const id = req.params.id;
    const paymentMethod = await PaymentMethodServices.findOne(id);
    if (paymentMethod) {
      res.status(200).send(paymentMethod);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
};

export default PaymentMethodAdminController;
