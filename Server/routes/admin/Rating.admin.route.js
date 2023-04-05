import RatingAdminController from '../../controllers/admin/Rating.admin.controller.js';
import express from 'express';

const router = express.Router();

router.post('/create', RatingAdminController.create)
router.post('/update/:id', RatingAdminController.update)
router.post('/delete/:id', RatingAdminController.delete)
router.post('/:id', RatingAdminController.findOne)
router.post('/', RatingAdminController.findAll)

export default router;
