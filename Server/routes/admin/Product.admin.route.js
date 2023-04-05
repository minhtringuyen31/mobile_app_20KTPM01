import ProductAdminController from '../../controllers/admin/Product.admin.controller.js';
import express from 'express';

const router = express.Router();

router.post('/create', ProductAdminController.create)
router.post('/update/:id', ProductAdminController.update)
router.post('/delete/:id', ProductAdminController.delete)
router.post('/:id', ProductAdminController.findOne)
router.post('/', ProductAdminController.findAll)

export default router;
