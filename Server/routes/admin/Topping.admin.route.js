import ToppingAdminController from '../../controllers/admin/Topping.admin.controller.js';
import express from 'express';

const router = express.Router();

router.post('/create', ToppingAdminController.create);
router.put('/update/:id', ToppingAdminController.update);
router.delete('/delete/:id', ToppingAdminController.delete);
router.get('/:id', ToppingAdminController.findOne);
router.get('/', ToppingAdminController.findAll);

export default router;
