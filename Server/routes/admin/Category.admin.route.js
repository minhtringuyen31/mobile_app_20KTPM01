import CategoryAdminController from '../../controllers/admin/Category.admin.controller.js';
import express from 'express';
import uploadCloud from '../../middlewares/uploader.js';

const router = express.Router();

router.post(
  '/create',
  uploadCloud.single('image'),
  CategoryAdminController.create
);
router.put(
  '/updateWithoutImage/:id',
  CategoryAdminController.updateWithoutImage
);
router.put(
  '/update/:id',
  uploadCloud.single('image'),
  CategoryAdminController.update
);
router.delete('/delete/:id', CategoryAdminController.delete);
router.get('/:id', CategoryAdminController.findOne);
router.get('/', CategoryAdminController.findAll);
router.put('/disable/:id', CategoryAdminController.disable);
router.put('/enable/:id', CategoryAdminController.enable);

export default router;
