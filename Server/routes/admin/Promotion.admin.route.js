import PromotionAdminController from '../../controllers/admin/Promotion.admin.controller.js';
import express from 'express';

const router = express.Router();

router.post('/create', PromotionAdminController.create)
router.put('/update/:id', PromotionAdminController.update)
router.delete('/delete/:id', PromotionAdminController.delete)
router.get('/:id', PromotionAdminController.findOne)
router.get('/', PromotionAdminController.findAll)
router.put('/disable/:id', PromotionAdminController.disable)
router.put('/enable/:id', PromotionAdminController.enable)

export default router;
