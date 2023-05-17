import RatingAdminController from '../../controllers/admin/Rating.admin.controller.js';
import express from 'express';

const router = express.Router();

router.post('/create', RatingAdminController.create)
router.put('/update/:id', RatingAdminController.update)
router.delete('/delete/:id', RatingAdminController.delete)
router.get('/:id', RatingAdminController.findOne)
router.get('/', RatingAdminController.findAll)
router.put('/disable/:id', RatingAdminController.disable)
router.put('/enable/:id', RatingAdminController.enable)

export default router;
