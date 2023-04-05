import PromotionAdminController from '../../controllers/admin/Promotion.admin.controller.js';
import express from 'express';

const router = express.Router();

router.post('/create', PromotionAdminController.create)
router.post('/update/:id', PromotionAdminController.update)
router.post('/delete/:id', PromotionAdminController.delete)
router.post('/:id', PromotionAdminController.findOne)
router.post('/', PromotionAdminController.findAll)

export default router;
