import RatingAdminController from '../../controllers/admin/Rating.admin.controller.js';
import express from 'express';

const router = express.Router();

router.post('/create', RatingAdminController.create)
router.put('/update/:id', RatingAdminController.update)
router.delete('/delete/:id', RatingAdminController.delete)
router.get('/:id', RatingAdminController.findOne)
router.get('/', RatingAdminController.findAll)

export default router;
