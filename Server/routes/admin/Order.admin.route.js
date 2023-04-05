import OrderAdminController from '../../controllers/admin/Order.admin.controller.js';
import express from 'express';

const router = express.Router();

router.post('/create', OrderAdminController.create)
router.post('/update/:id', OrderAdminController.update)
router.post('/delete/:id', OrderAdminController.delete)
router.post('/:id', OrderAdminController.findOne)
router.post('/', OrderAdminController.findAll)

export default router;
