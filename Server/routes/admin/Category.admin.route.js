import CategoryAdminController from '../../controllers/admin/Category.admin.controller.js';
import express from 'express';

const router = express.Router();

router.post('/create', CategoryAdminController.create)
router.put('/update/:id', CategoryAdminController.update)
router.delete('/delete/:id', CategoryAdminController.delete)
router.get('/:id', CategoryAdminController.findOne)
router.get('/', CategoryAdminController.findAll)
router.put('/disable/:id', CategoryAdminController.disable)
router.put('/enable/:id', CategoryAdminController.enable)

export default router;
