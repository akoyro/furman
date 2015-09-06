package by.dak.report.jasper.store;

import by.dak.cutting.agt.report.AGTFurnitureDataConverter;
import by.dak.cutting.zfacade.ZButtLink;
import by.dak.cutting.zfacade.ZFacade;
import by.dak.cutting.zfacade.report.ZFacadeFurnitureDataConverter;
import by.dak.persistence.FacadeContext;
import by.dak.persistence.entities.AOrder;
import by.dak.persistence.entities.FurnitureLink;
import by.dak.persistence.entities.predefined.OrderItemType;
import by.dak.report.jasper.OrderHeaderReportDataCreator;
import by.dak.report.jasper.common.data.CommonData;
import by.dak.report.jasper.common.data.CommonDataType;
import by.dak.report.jasper.common.data.CommonDatas;
import by.dak.report.jasper.common.data.CommonReportData;
import by.dak.report.jasper.common.data.converter.FurnitureConverter;

import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;


/**
 * @author Denis Koyro
 * @version 0.1 22.03.2009
 * @introduced [Furniture constructor | Iteration 1]
 * @since 1.0.0
 */
public class StoreReportDataCreator extends OrderHeaderReportDataCreator
{

    private static final Logger LOGGER = Logger.getLogger(StoreReportDataCreator.class.getName());

    private static final String JASPER_REPORT_PATH = "StoreReport.jasper";
    private static final String REPORT_BUNDLES_PATH = "by/dak/report/jasper/common/commonReport";

    private CommonReportData commonReportData;

    public StoreReportDataCreator(CommonReportData commonReportData)
    {
        super(commonReportData.getOrder());
        this.commonReportData = commonReportData;
    }

    public StoreReportDataCreator(AOrder order)
    {
        super(order);
    }


    @Override
    public URL getJasperReportPath()
    {
        return StoreReportDataCreator.class.getResource(JASPER_REPORT_PATH);
    }

    @Override
    public String getReportBundlesPath()
    {
        return REPORT_BUNDLES_PATH;
    }

    @Override
    public Collection createCollection()
    {
        List<FurnitureLink> furnitureLinks = FacadeContext.getFurnitureLinkFacade().loadAllBy(getOrder(),
                Arrays.asList(new OrderItemType[]{OrderItemType.common, OrderItemType.first}));
        CommonDatas<CommonData> datas = new FurnitureConverter(CommonDataType.furniture, getOrder()).convert(furnitureLinks);
        furnitureLinks = FacadeContext.getFurnitureLinkFacade().loadAllBy(getOrder(), Arrays.asList(new OrderItemType[]{OrderItemType.zfacade}), ZButtLink.class.getSimpleName());
        datas.addAll(new FurnitureConverter(CommonDataType.furniture, getOrder()).convert(furnitureLinks));

        ZFacadeFurnitureDataConverter zFacadeFurnitureDataConverter = new ZFacadeFurnitureDataConverter(getOrder());
        datas.addAll(
                zFacadeFurnitureDataConverter.convert(FacadeContext.getZFacadeFacade().fillTransients(FacadeContext.getZFacadeFacade().findAllByField(ZFacade.PROPERTY_order, getOrder())))
        );

        AGTFurnitureDataConverter agtFurnitureDataConverter = new AGTFurnitureDataConverter(getOrder());
        datas.addAll(
                agtFurnitureDataConverter.convert(FacadeContext.getAGTFacadeFacade().fillTransients(FacadeContext.getAGTFacadeFacade().findAllByField(ZFacade.PROPERTY_order, getOrder())))
        );

        return datas;
    }
}