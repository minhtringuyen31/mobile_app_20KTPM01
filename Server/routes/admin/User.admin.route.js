import UserAdminController from '../../controllers/admin/User.admin.controller.js';
import express from 'express';

const router = express.Router();

router.post('/create', UserAdminController.create)
router.put('/update/:id', UserAdminController.update)
router.delete('/delete/:id', UserAdminController.delete)
router.get('/:id', UserAdminController.findOne)
router.get('/', UserAdminController.findAll)

export default router;
