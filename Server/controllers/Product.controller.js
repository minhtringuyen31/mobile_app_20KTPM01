import ProductService from "../services/Product.service.js"
const ProductController = {
    async create(req, res) {

        const { name, description, size, price_S, price_M, price_L, image, status, category_id, update_date, release_date, sales } = req.body;
        const newProduct = await ProductService.create(name, description, size, price_S, price_M, price_L, image, status, category_id, update_date, release_date, sales);
        if (newProduct) {
            res.status(200).send(newProduct)
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }

    },
    async update(req, res) {
        const id = req.params.id;
        const { name, description, size, price_S, price_M, price_L, image, status, category_id, update_date, release_date, sales } = req.body;
        const updateProduct = await ProductService.update(id, name, description, size, price_S, price_M, price_L, image, status, category_id, update_date, release_date, sales);
        if (updateProduct) {
            res.status(200).send(updateProduct)
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }

    },
    async delete(req, res) {
        const id = req.params.id;
        const status = await ProductService.delete(id);
        if (status) {
            res.status(200).send({ status: 1, message: "Success" })
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }


    },
    async findOne(req, res) {
        const id = req.params.id;
        const product = await ProductService.findOne(id);
        if (product) {
            res.status(200).send(product)
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }

    },
    async findAll(req, res) {
        const products = await ProductService.findAll();
        if (products) {
            res.status(200).send(products)
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }

    },
    async findAllFavProduct(req, res) {
        console.log("Find All Fav Product Controller")
        const userId = req.params.userId
        const products = await ProductService.findAllFavProduct(userId)
        console.log("response" + products)
        if (products) {
            res.status(200).send(products)
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }
    },
    async addNewFavProduct(req, res) {
        const { userId, productId } = req.body
        console.log("Add New Fav Product" + userId + productId)
        const product = await ProductService.addNewFavProduct(userId, productId)
        if (product) {
            res.status(200).send(product)
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }
    },
    async removeFavProduct(req, res) {
        const { userId, productId } = req.body
        console.log("Remove Fav Product" + userId + productId)
        const product = await ProductService.removeFavProduct(userId, productId)
        if (product) {
            res.status(200).send(true)
        } else {
            res.status(404).send(false);
        }
    },
    async isExistedFavProduct(req, res) {
        const userId = req.query.userId
        const productId = req.query.productId
        console.log("Check Fav Product" + userId + productId)
        const product = await ProductService.isExistedFavProduct(userId, productId)
        if (product) {
            res.status(200).send(true)
        } else {
            res.status(404).send(false);
        }
    },
    async getRating(req, res) {
        const productId = req.params.productId
        const result = await ProductService.getRating(productId)
        if (result) {
            res.status(200).send(result)
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }
    },
    async findAllSales(req, res) {
        console.log("dwgsdaf")
        const orders = await ProductService.findAllSales();
        if (orders) {
            res.status(200).send(orders);
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }
    },
    test(req, res) {
        res.send("Test API from Product")
    },


}

export default ProductController;