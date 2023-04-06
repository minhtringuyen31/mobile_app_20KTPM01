import ProductAdminController from '../../controllers/admin/Product.admin.controller.js';
import express from 'express';

const router = express.Router();

router.post('/create', ProductAdminController.create)
router.put('/update/:id', ProductAdminController.update)
router.delete('/delete/:id', ProductAdminController.delete)
router.get('/:id', ProductAdminController.findOne)
router.get('/', ProductAdminController.findAll)

export default router;
