import PromotionAdminController from '../../controllers/admin/Promotion.admin.controller.js';
import express from 'express';
import uploadCloud from '../../middlewares/uploader.js';

const router = express.Router();

router.post(
  '/create',
  uploadCloud.single('image'),
  PromotionAdminController.create
);
router.put(
  '/update/:id',
  uploadCloud.single('image'),
  PromotionAdminController.update
);
router.put('/updateWithoutImage/:id', PromotionAdminController.updateWithoutImage);
router.delete('/delete/:id', PromotionAdminController.delete);
router.get('/:id', PromotionAdminController.findOne);
router.get('/', PromotionAdminController.findAll);
router.put('/disable/:id', PromotionAdminController.disable);
router.put('/enable/:id', PromotionAdminController.enable);

export default router;
