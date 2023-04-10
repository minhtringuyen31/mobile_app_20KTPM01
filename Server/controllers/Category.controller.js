import CategoryService from "../services/Category.service.js"

const CategoryController = {
    async create(req, res) {
        const { name } = req.body;
        const file = req.file || '';
        const newCategory = await CategoryService.create(name, file);
        if (newCategory) {
            res.status(200).send(newCategory)
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }

    },
    async update(req, res) {
        const id = req.params.id;
        const { name } = req.body;
        const updateCategory = await CategoryService.update(id, name);
        if (updateCategory) {
            res.status(200).send(updateCategory)
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }

    },
    async delete(req, res) {
        const id = req.params.id;
        const status = await CategoryService.delete(id);
        if (status) {
            res.status(200).send({ status: 1, message: "Success" })
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }


    },
    async findOne(req, res) {
        const id = req.params.id;
        const category = await CategoryService.findOne(id);
        if (category) {
            res.status(200).send(category)
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }

    },
    async findAll(req, res) {
        const categories = await CategoryService.findAll();
        if (categories) {
            res.status(200).send(categories)
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }

    },
    async uploadImage(req, res) {
        try {
            const file = req.file;
            console.log(file);
            res.status(200).json("ok")
        } catch (error) {
            res.status(404).json("sai")
        }

    },
    test(req, res) {
        res.send("Test API from Category")
    }


}

export default CategoryController;