import ProductAdminController from '../../controllers/admin/Product.admin.controller.js';
import express from 'express';

const router = express.Router();

router.post('/create', ProductAdminController.create)
router.put('/update/:id', ProductAdminController.update)
router.delete('/delete/:id', ProductAdminController.delete)
router.get('/:id', ProductAdminController.findOne)
router.get('/', ProductAdminController.findAll)
router.put('/disable/:id', ProductAdminController.disable)
router.put('/enable/:id', ProductAdminController.enable)
router.put('/available/:id', ProductAdminController.available)
router.put('/unavailable/:id', ProductAdminController.unavailable)

export default router;
