import PromotionService from "../services/Promotion.service.js"
const PromotionController = {
    async create(req, res) {

        const { name, description, discount, start_date, end_date }=req.body;
        const newPromotion = await PromotionService.create(name, description, discount, start_date, end_date);
        if (newPromotion) {
            res.status(200).send(newPromotion)
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }

    },
    async update(req, res) {
        const id = req.params.id;
        const { name, description, discount, start_date, end_date } = req.body;
        const updatePromotion = await PromotionService.update(id,name, description, discount, start_date, end_date);
        if (updatePromotion) {
            res.status(200).send(updatePromotion)
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }

    },
    async delete(req, res) {
        const id = req.params.id;
        const status = await PromotionService.delete(id);
        if (status) {
            res.status(200).send({ status: 1, message: "Success" })
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }


    },
    async findOne(req, res) {
        const id = req.params.id;
        const promotion = await PromotionService.findOne(id);
        if (promotion) {
            res.status(200).send(promotion)
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }

    },
    async findAll(req, res) {
        const promotions = await PromotionService.findAll();
        if (promotions) {
            res.status(200).send(promotions)
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }

    },
    test(req, res) {
        res.send("Test API from Promotion")
    }


}

export default PromotionController;