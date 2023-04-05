import ProductServices from '../../services/Product.service.js';

const ProductAdminController = {
  create: async (req, res) => {
    const {
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
      sales,
    } = req.body;
    const newProduct = await ProductServices.create(
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
    if (newProduct) {
      res.status(200).send(newProduct);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  update: async (req, res) => {
    const id = req.params.id;
    const {
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
      sales,
    } = req.body;
    const updateProduct = await ProductServices.update(
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
    if (updateProduct) {
      res.status(200).send(updateProduct);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  delete: async (req, res) => {
    const id = req.params.id;
    const status = await ProductServices.delete(id);
    if (status) {
      res.status(200).send({ status: 1, message: 'Success' });
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  findOne: async (req, res) => {
    const id = req.params.id;
    const product = await ProductServices.findOne(id);
    if (product) {
      res.status(200).send(product);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
  findAll: async (req, res) => {
    const products = await ProductServices.findAll();
    if (products) {
      res.status(200).send(products);
    } else {
      res.status(404).send({ status: 0, message: 'Failed' });
    }
  },
};

export default ProductAdminController;
