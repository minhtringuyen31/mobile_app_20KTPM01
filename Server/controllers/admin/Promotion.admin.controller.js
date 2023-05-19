import PromotionServices from '../../services/Promotion.service.js';

const PromotionAdminController = {
  countPromotion: async (req, res) => {
    const count = await PromotionServices.countPromotion();
    if (count) {
      res.status(200).send({ count });
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  disable: async (req, res) => {
    const id = req.params.id;
    const status = await PromotionServices.changeIsDisable(id, 1);
    if (status) {
      res.status(200).send({ status: 1, message: 'Success' });
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  enable: async (req, res) => {
    const id = req.params.id;
    const status = await PromotionServices.changeIsDisable(id, 0);
    if (status) {
      res.status(200).send({ status: 1, message: 'Success' });
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  create: async (req, res) => {
    const {
      name,
      description,
      discount,
      start_date,
      end_date,
      quantity,
      code,
    } = req.body;
    const file = req.file || '';
    const newPromotion = await PromotionServices.create(
      name,
      description,
      discount,
      start_date,
      end_date,
      quantity,
      code,
      file
    );
    if (newPromotion) {
      res.status(200).send(newPromotion);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  updateWithoutImage: async (req, res) => {
    const id = req.params.id;
    const {
      name,
      description,
      discount,
      start_date,
      end_date,
      quantity,
      code,
    } = req.body;
    const updatePromotion = await PromotionServices.updateWithoutImage(
      id,
      name,
      description,
      discount,
      start_date,
      end_date,
      quantity,
      code
    );
    if (updatePromotion) {
      res.status(200).send(updatePromotion);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  update: async (req, res) => {
    const id = req.params.id;
    const {
      name,
      description,
      discount,
      start_date,
      end_date,
      quantity,
      code,
    } = req.body;
    const image = req.file || '';
    const updatePromotion = await PromotionServices.update(
      id,
      name,
      description,
      discount,
      start_date,
      end_date,
      image,
      quantity,
      code
    );
    if (updatePromotion) {
      res.status(200).send(updatePromotion);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  delete: async (req, res) => {
    const id = req.params.id;
    const status = await PromotionServices.delete(id);
    if (status) {
      res.status(200).send({ status: 1, message: 'Success' });
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  findOne: async (req, res) => {
    const id = req.params.id;
    const promotion = await PromotionServices.findOne(id);
    if (promotion) {
      res.status(200).send(promotion);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  findAll: async (req, res) => {
    const promotions = await PromotionServices.findAll();
    if (promotions) {
      res.status(200).send(promotions);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
};

export default PromotionAdminController;
