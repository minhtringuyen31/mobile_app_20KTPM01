import OrderAdminController from '../../controllers/admin/Order.admin.controller.js';
import express from 'express';

const router = express.Router();
router.get("/bymonth/:month/:year", OrderAdminController.findAllByMonthAndYear)
router.get("/bydate", OrderAdminController.findAllByDate)
router.get("/byweek", OrderAdminController.findAllByWeek)
router.get("/bymonth", OrderAdminController.findAllByMonth)
router.get('/total', OrderAdminController.totalOrder);
router.get('/count', OrderAdminController.countOrder);
router.post('/create', OrderAdminController.create);
router.put('/update/:id', OrderAdminController.update);
router.delete('/delete/:id', OrderAdminController.delete);
router.get('/:id', OrderAdminController.findOne);
router.get('/', OrderAdminController.findAll);
router.put('/deny/:id', OrderAdminController.changeDenyStatus);
router.put('/accept/:id', OrderAdminController.changeAcceptStatus);
router.put('/delivered/:id', OrderAdminController.changeDeliveredStatus);

export default router;
