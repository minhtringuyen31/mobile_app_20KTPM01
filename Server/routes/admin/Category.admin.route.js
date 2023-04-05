import CategoryAdminController from '../../controllers/admin/Category.admin.controller.js';
import express from 'express';

const router = express.Router();

router.post('/create', CategoryAdminController.create)
router.post('/update/:id', CategoryAdminController.update)
router.post('/delete/:id', CategoryAdminController.delete)
router.post('/:id', CategoryAdminController.findOne)
router.post('/', CategoryAdminController.findAll)

export default router;
