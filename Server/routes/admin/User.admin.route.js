import UserAdminController from '../../controllers/admin/User.admin.controller.js';
import express from 'express';

const router = express.Router();

router.get('/count', UserAdminController.countUser);
router.post('/create', UserAdminController.create);
router.put('/update/:id', UserAdminController.update);
router.delete('/delete/:id', UserAdminController.delete);
router.get('/:id', UserAdminController.findOne);
router.get('/', UserAdminController.findAll);
router.put('/disable/:id', UserAdminController.disable);
router.put('/enable/:id', UserAdminController.enable);

export default router;
