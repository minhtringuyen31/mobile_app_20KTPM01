import express from 'express';
import PaymentMethodAdminController from '../../controllers/admin/PaymentMethod.admin.controller.js';

const router = express.Router();

router.get('/:id', PaymentMethodAdminController.findOne);

export default router;