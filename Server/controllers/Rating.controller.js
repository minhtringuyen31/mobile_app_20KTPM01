import RatingService from "../services/Rating.service.js"
const RatingController = {
    async create(req, res) {
        const { user_id, product_id, score, comment, create_at } = req.body;
        const newRating = await RatingService.create(user_id, product_id, score, comment, create_at);
        if (newRating) {
            res.send(newRating)
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }

    },
    async update(req, res) {
        const id = req.params.id;
        const { user_id, product_id, score, comment, create_at } = req.body;
        const updateRating = await RatingService.update(id, user_id, product_id, score, comment, create_at);
        if (updateRating) {
            res.send(newRating)
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }
    },
    async delete(req, res) {
        const id = req.params.id;
        const deleteRating = await RatingService.delete(id);
        if (deleteRating) {
            res.status(200).send({ status: 1, message: "Success" })
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }


    },
    async findRating(req, res) {
        const id = req.params.id;
        const rating = await RatingService.findRating(id);
        if (rating) {
            res.status(200).send(rating)
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }

    },
    async findAll(req, res) {
        const ratings = await RatingService.findAll();
        if (ratings) {
            res.status(200).send(ratings)
        } else {
            res.status(404).send({ status: 0, message: "Failed" });
        }

    },
    test(req, res) {
        res.send("Test API from Rating")
    }


}

export default RatingController;