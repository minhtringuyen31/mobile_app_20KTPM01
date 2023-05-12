import OrderAdminController from '../../controllers/admin/Order.admin.controller.js';
import express from 'express';

const router = express.Router();

router.post('/create', OrderAdminController.create);
router.put('/update/:id', OrderAdminController.update);
router.delete('/delete/:id', OrderAdminController.delete);
router.get('/:id', OrderAdminController.findOne);
router.get('/', OrderAdminController.findAll);
router.put('/delivering/:id', OrderAdminController.changeDeliveringStatus);
router.put('/delivered/:id', OrderAdminController.changeDeliveredStatus);

export default router;
