import UserAdminController from '../../controllers/admin/User.admin.controller.js';
import express from 'express';

const router = express.Router();

router.post('/create', UserAdminController.create)
router.post('/update/:id', UserAdminController.update)
router.post('/delete/:id', UserAdminController.delete)
router.post('/:id', UserAdminController.findOne)
router.post('/', UserAdminController.findAll)

export default router;
