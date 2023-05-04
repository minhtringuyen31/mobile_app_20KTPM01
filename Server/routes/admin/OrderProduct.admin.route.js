import OrderProductAdminController from '../../controllers/admin/OrderProduct.admin.controller.js';
import express from 'express';

const router = express.Router();

router.post('/create', OrderProductAdminController.create);
router.put('/update/:id', OrderProductAdminController.update);
router.delete('/delete/:id', OrderProductAdminController.delete);
router.get('/:id', OrderProductAdminController.findOne);
router.get('/', OrderProductAdminController.findAll);
router.get('/order/:order_id', OrderProductAdminController.findAllByOrderID);

export default router;