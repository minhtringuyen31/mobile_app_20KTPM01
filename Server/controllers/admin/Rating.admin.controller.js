import RatingServices from '../../services/Rating.service.js';

const RatingAdminController = {
  countRating: async (req, res) => {
    const count = await RatingServices.countRating();
    if (count) {
      res.status(200).send({ count });
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  disable: async (req, res) => {
    const id = req.params.id;
    const ratingDisable = await RatingServices.changeIsDisable(id, 1);
    if (ratingDisable) {
      res.status(200).send(ratingDisable);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  enable: async (req, res) => {
    const id = req.params.id;
    const ratingEnable = await RatingServices.changeIsDisable(id, 0);
    if (ratingEnable) {
      res.status(200).send(ratingEnable);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  create: async (req, res) => {
    const { user_id, product_id, score, comment, create_at } = req.body;
    const newRating = await RatingServices.create(
      user_id,
      product_id,
      score,
      comment,
      create_at
    );
    if (newRating) {
      res.send(newRating);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  update: async (req, res) => {
    const id = req.params.id;
    const { user_id, product_id, score, comment, create_at } = req.body;
    const updateRating = await RatingServices.update(
      id,
      user_id,
      product_id,
      score,
      comment,
      create_at
    );
    if (updateRating) {
      res.send(newRating);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  delete: async (req, res) => {
    const id = req.params.id;
    const deleteRating = await RatingServices.delete(id);
    if (deleteRating) {
      res.status(200).send({ status: 1, message: 'Success' });
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  findOne: async (req, res) => {
    const id = req.params.id;
    const rating = await RatingServices.findOne(id);
    if (rating) {
      res.status(200).send(rating);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  findAll: async (req, res) => {
    const ratings = await RatingServices.findAll();
    if (ratings) {
      res.status(200).send(ratings);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
};

export default RatingAdminController;
