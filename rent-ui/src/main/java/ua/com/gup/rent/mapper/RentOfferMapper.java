package ua.com.gup.rent.mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.rent.model.enumeration.RentOfferStatus;
import ua.com.gup.rent.model.mongo.rent.RentOffer;
import ua.com.gup.rent.model.rent.RentOfferLands;
import ua.com.gup.rent.model.rent.RentOfferModerationReport;
import ua.com.gup.rent.model.rent.category.attribute.*;
import ua.com.gup.rent.model.rent.statistic.RentOfferStatistic;
import ua.com.gup.rent.service.category.RentOfferCategoryService;
import ua.com.gup.rent.service.category.attribute.RentOfferCategoryAttributeService;
import ua.com.gup.rent.service.dto.category.attribute.RentOfferCategoryAttributeDTO;
import ua.com.gup.rent.service.dto.category.attribute.RentOfferCategoryAttributeValueDTO;
import ua.com.gup.rent.service.dto.rent.RentOfferModerationReportDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferCreateDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferLandsDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferUpdateDTO;
import ua.com.gup.rent.service.dto.rent.offer.price.RentOfferPriceDTO;
import ua.com.gup.rent.service.dto.rent.offer.statistic.RentOfferStatisticByDateDTO;
import ua.com.gup.rent.service.dto.rent.offer.view.*;
import ua.com.gup.rent.util.RentDateUtil;
import ua.com.gup.rent.util.security.RentSecurityUtils;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class RentOfferMapper {

  /*  @Autowired
    private RentOfferPriceMapper priceMapper;*/

  /*  @Autowired
    private AddressMapper addressMapper;*/

   /* @Autowired
    private OfferContactInfoMapper contactInfoMapper;*/

    @Autowired
    private RentOfferAuthorMapper authorMapper;

    @Autowired
    private RentOfferCategoryMapper offerCategoryMapper;

    @Autowired
    private RentOfferCategoryService categoryService;

    @Autowired
    private RentOfferCategoryAttributeService categoryAttributeService;

    @Autowired
    private RentOfferPriceMapper rentOfferPriceMapper;

    private static final int PRICE_ATTRIBUTE_CODE = 1;



    public RentOffer offerCreateDTOToOffer(RentOfferCreateDTO offerCreateDTO) {
        RentOffer offer = new RentOffer();
        fromOfferCreateDTOToOffer(offerCreateDTO, offer);
        return offer;
    }

    public void offerUpdateDTOToOffer(RentOfferUpdateDTO source, RentOffer target) {
        fromOfferCreateDTOToOffer(source, target);
        if (source.getCategory() != null) {
            target.setCategories(categoryService.getRentOfferCategoriesIds(source.getCategory()));
        }
        /*if (source.getAddress() != null) {
            target.setAddress(addressMapper.addressDTOToAddress(source.getAddress()));
        }*/
        /*if (source.getImages() != null && source.getImages().size() > 0) {
            List<String> imageIds = new LinkedList<>();
            source.getImages().forEach(i -> imageIds.add(i.getImageId()));
            target.setImageIds(imageIds);
        }*/
        /*if (source.getYoutubeVideoId() != null) {
            target.setYoutubeVideoId(source.getYoutubeVideoId());
        }*/
       /* if (source.getContactInfo() != null) {
            target.setContactInfo(contactInfoMapper.contactInfoDTOToContactInfo(source.getContactInfo()));
        }*/
    }

    public void offerModeratorDTOToOffer(RentOfferModerationReportDTO source, RentOffer target) {
        if (source.getCategory() != null) {
            target.setCategories(categoryService.getRentOfferCategoriesIds(source.getCategory()));
        }
        RentOfferModerationReport moderationReport = new RentOfferModerationReport();
        moderationReport.setDescription(source.getDescription());
        moderationReport.setRefusalReasons(source.getRefusalReasons());
        moderationReport.setModeratorId(RentSecurityUtils.getCurrentUserId());
        moderationReport.setLastModifiedDate(ZonedDateTime.now());
        target.setLastOfferModerationReport(moderationReport);
    }

    public RentOfferViewShortDTO offerToOfferShortDTO(RentOffer offer) {
        RentOfferViewShortDTO offerViewShortDTO = new RentOfferViewShortDTO();
        fromOfferToOfferViewShortDTO(offer, offerViewShortDTO);
        return offerViewShortDTO;
    }

    public RentOfferViewCoordinatesDTO offerToOfferCoordinatesDTO(RentOffer offer) {
        RentOfferViewCoordinatesDTO coordinatesDTO = null;
        /*if (offer.getAddress() != null && offer.getAddress().getLat() != null  && offer.getAddress().getLng() != null) {*/
            coordinatesDTO = new RentOfferViewCoordinatesDTO(offer.getSeoUrl(),null /*new BigDecimal[]{offer.getAddress().getLat(), offer.getAddress().getLng()}*/);
        /*}*/
        return coordinatesDTO;
    }

    public RentOfferViewShortWithModerationReportDTO offerToOfferViewShortWithModerationReportDTO(RentOffer offer) {
        RentOfferViewShortWithModerationReportDTO offerViewShortWithModerationReportDTO = new RentOfferViewShortWithModerationReportDTO();
        fromOfferToOfferViewShortDTO(offer, offerViewShortWithModerationReportDTO);
        offerViewShortWithModerationReportDTO.setModerationReport(offer.getLastOfferModerationReport());
        return offerViewShortWithModerationReportDTO;
    }


    public RentOfferViewDetailsDTO offerToOfferDetailsDTO(RentOffer offer) {
        RentOfferViewDetailsDTO offerViewDetailsDTO = new RentOfferViewDetailsDTO();
        fromOfferToOfferViewBaseDTO(offer, offerViewDetailsDTO);
        offerViewDetailsDTO.setAttrs(offer.getAttrs());
        offerViewDetailsDTO.setMultiAttrs(offer.getMultiAttrs());
        offerViewDetailsDTO.setNumAttrs(offer.getNumAttrs());
        offerViewDetailsDTO.setBoolAttrs(offer.getBoolAttrs());
        offerViewDetailsDTO.setStatus(offer.getStatus());
        //offerViewDetailsDTO.setAddress(addressMapper.addressToAddressDTO(offer.getAddress()));
        if (offer.getRentOfferPrice() != null) {
            RentOfferPriceDTO priceDTO = new RentOfferPriceDTO();
          //  priceMapper.moneyToMoneyDTO(offer.getRentOfferPrice(), priceDTO);
            offerViewDetailsDTO.setPrice(priceDTO);
        }
        //offerViewDetailsDTO.setYoutubeVideoId(offer.getYoutubeVideoId());
        
        //owner ? doesn't hide phone number : hide phone number
       /* boolean hidePhoneNumber =   !RentSecurityUtils.isAuthenticated() || !(RentSecurityUtils.getCurrentUserId().equals(offer.getAuthorId()));
        offerViewDetailsDTO.setContactInfo(contactInfoMapper.contactInfoToContactInfoDTO(offer.getContactInfo(), hidePhoneNumber));*/
        
        /*offerViewDetailsDTO.setOfferStatistic(new RentOfferStatisticDTO(offer.getStatistic().getTotalOfferViewsCount(), offer.getStatistic().getTotalOfferPhonesViewsCount()));
        if (offer.getLands() != null) {
            offerViewDetailsDTO.setLands(transformLandsToOfferLandsDTO(offer.getLands()));
        }*/

        return offerViewDetailsDTO;
    }

    private void fromOfferToOfferViewShortDTO(RentOffer source, RentOfferViewShortDTO target) {
        fromOfferToOfferViewBaseDTO(source, target);
       // target.setAddress(addressMapper.addressToAddressDTO(source.getAddress()));
        /*if (source.getRentOfferPrice() != null) {
            RentOfferPriceShortDTO priceDTO = new RentOfferPriceShortDTO();
            priceMapper.moneyToMoneyDTO(source.getPrice(), priceDTO);
            Optional<OfferCategorySingleAttributeValue> collect = source.getAttrs().values().stream().filter(a -> a.getCode() == PRICE_ATTRIBUTE_CODE).findFirst();
            if (collect.isPresent()) {
                OfferCategorySingleAttributeValue priceAttributes = collect.get();
                priceDTO.setSelected(priceAttributes.getSelected());
                priceDTO.setTitle(priceAttributes.getTitle());
            }
            target.setPrice(priceDTO);
        }
        if (source.getLands() != null) {
            target.setLands(transformLandsToOfferLandsDTO(source.getLands()));
        }*/
    }

    public List<RentOfferStatisticByDateDTO> offerStatisticToOfferStatisticDTO(RentOfferStatistic viewStatistic, LocalDate offerDtCreate, LocalDate dateStart, LocalDate dateEnd) {
        List<LocalDate> dates = RentDateUtil.getDateRangeBetweenDates(dateStart, dateEnd, true);
        List<RentOfferStatisticByDateDTO> statistic = new ArrayList<>();
        for (LocalDate date : dates) {
            RentOfferStatisticByDateDTO statisticByDateDTO = new RentOfferStatisticByDateDTO();
            statisticByDateDTO.setDate(date.toString());
            statisticByDateDTO.setOfferViews(viewStatistic.getOfferViewsCountByDate(offerDtCreate, date));
            statisticByDateDTO.setOfferPhonesViews(viewStatistic.getOfferPhonesViewsCountByDate(offerDtCreate, date));
            statistic.add(statisticByDateDTO);
        }
        return statistic;
    }


    private void fromOfferToOfferViewBaseDTO(RentOffer source, RentOfferViewBaseDTO target) {
        target.setId(source.getId());
       // target.setLastModifiedDate(source.getLastModifiedDate());
        target.setAuthor(authorMapper.createAuthorDTO(source.getAuthorId()));
        if (source.getCategories() != null) {
            target.setCategories(offerCategoryMapper.offerCategoriesByCategoriesIds(source.getCategories()));
        }
        target.setTitle(source.getTitle());
        target.setDescription(source.getDescription());
        /*if (source.getImageIds() != null && source.getImageIds().size() > 0) {
            LinkedList<String> imageIds = new LinkedList<String>();
            source.getImageIds().forEach(i -> imageIds.add(i));
            target.setImageIds(imageIds);
        }*/
        target.setSeoUrl(source.getSeoUrl());

    }

    private void fromOfferCreateDTOToOffer(RentOfferCreateDTO source, RentOffer target) {

        if (source.getCategory() != null) {
            target.setCategories(categoryService.getRentOfferCategoriesIds(source.getCategory()));
        }

        if (source.getTitle() != null) {
            target.setTitle(source.getTitle());
        }

        if (source.getDescription() != null) {
            target.setDescription(source.getDescription());
        }

        /*if (source.getAddress() != null) {
            target.setAddress(addressMapper.addressDTOToAddress(source.getAddress()));
        }*/

        /*if (source.getPrice() != null) {
            target.setPrice(priceMapper.moneyDTOToMoney(source.getPrice()));
        }*/

        /*if (source.getImages() != null && source.getImages().size() > 0) {
            List<String> imageIds = new LinkedList<String>();
            source.getImages().forEach(i -> imageIds.add(i.getImageId()));
            target.setImageIds(imageIds);
        }*/

        /*if (source.getLands() != null) {
            target.setLands(transformOfferLandsDTOToLands(source.getLands()));
        }*/

        /*if (source.getYoutubeVideoId() != null) {
            target.setYoutubeVideoId(source.getYoutubeVideoId());
        }*/

        /*if (source.getContactInfo() != null) {
            target.setContactInfo(contactInfoMapper.contactInfoDTOToContactInfo(source.getContactInfo()));
        }*/

        if (source.getCategory() != null) {

            final SortedSet<RentOfferCategoryAttributeDTO> categoryAttributeDTOS = categoryAttributeService.findAllCategoryAttributeDTO().get(source.getCategory());

            final Map<String, RentOfferCategoryAttributeDTO> categoryAttributeDTOMap = categoryAttributeDTOS.stream().collect(Collectors.toMap(RentOfferCategoryAttributeDTO::getKey, Function.identity()));

            if (source.getAttrs() != null) {

                LinkedHashMap<String, RentOfferCategorySingleAttributeValue> attrs = new LinkedHashMap<>();

                for (String key : source.getAttrs().keySet()) {

                    final String value = source.getAttrs().get(key);
                    final RentOfferCategoryAttributeDTO categoryAttributeDTO = categoryAttributeDTOMap.get(key);
                    RentOfferCategorySingleAttributeValue attributeValue = new RentOfferCategorySingleAttributeValue();
                    fromCategoryAttributeDTOToOfferCategoryAttributeValue(categoryAttributeDTO, attributeValue);
                    RentOfferCategoryAttributeValue selected = new RentOfferCategoryAttributeValue();
                    selected.setKey(value);
                    for (RentOfferCategoryAttributeValueDTO valueDTO : categoryAttributeDTO.getValues()) {
                        if (value.equals(valueDTO.getKey())) {
                            selected.setTitle(valueDTO.getTitle());
                        }
                    }
                    attributeValue.setSelected(selected);
                    attrs.put(key, attributeValue);
                }
                target.setAttrs(attrs);
            }

            if (source.getMultiAttrs() != null) {
                LinkedHashMap<String, RentOfferCategoryMultiAttributeValue> multiAttrs = new LinkedHashMap<>();
                for (String key : source.getMultiAttrs().keySet()) {
                    RentOfferCategoryMultiAttributeValue attributeValue = new RentOfferCategoryMultiAttributeValue();
                    final RentOfferCategoryAttributeDTO categoryAttributeDTO = categoryAttributeDTOMap.get(key);
                    fromCategoryAttributeDTOToOfferCategoryAttributeValue(categoryAttributeDTO, attributeValue);
                    final String[] values = source.getMultiAttrs().get(key).split(",");
                    LinkedHashSet<RentOfferCategoryAttributeValue> selected = new LinkedHashSet<>();
                    for (String value : values) {
                        RentOfferCategoryAttributeValue selectedItem = new RentOfferCategoryAttributeValue();
                        selectedItem.setKey(value);
                        for (RentOfferCategoryAttributeValueDTO valueDTO : categoryAttributeDTO.getValues()) {
                            if (value.equals(valueDTO.getKey())) {
                                selectedItem.setTitle(valueDTO.getTitle());
                            }
                        }
                        selected.add(selectedItem);
                    }
                    attributeValue.setSelected(selected);
                    multiAttrs.put(key, attributeValue);
                }
                target.setMultiAttrs(multiAttrs);
            }

            if (source.getNumAttrs() != null) {
                LinkedHashMap<String, RentOfferCategoryNumericAttributeValue> numericAttrs = new LinkedHashMap<>();
                for (String key : source.getNumAttrs().keySet()) {
                    RentOfferCategoryNumericAttributeValue attributeValue = new RentOfferCategoryNumericAttributeValue();
                    final RentOfferCategoryAttributeDTO categoryAttributeDTO = categoryAttributeDTOMap.get(key);
                    fromCategoryAttributeDTOToOfferCategoryAttributeValue(categoryAttributeDTO, attributeValue);
                    attributeValue.setSelected(source.getNumAttrs().get(key));
                    attributeValue.setSelectedDouble(source.getNumAttrs().get(key).doubleValue());
                    numericAttrs.put(key, attributeValue);
                }
                target.setNumAttrs(numericAttrs);
            }

            if (source.getBoolAttrs() != null) {
                LinkedHashMap<String, RentOfferCategoryBoolAttributeValue> boolAttrs = new LinkedHashMap<>();
                for (String key : source.getBoolAttrs().keySet()) {
                    RentOfferCategoryBoolAttributeValue attributeValue = new RentOfferCategoryBoolAttributeValue();
                    final RentOfferCategoryAttributeDTO categoryAttributeDTO = categoryAttributeDTOMap.get(key);
                    fromCategoryAttributeDTOToOfferCategoryAttributeValue(categoryAttributeDTO, attributeValue);
                    attributeValue.setSelected(source.getBoolAttrs().get(key));
                    boolAttrs.put(key, attributeValue);
                }
                target.setBoolAttrs(boolAttrs);
            }
        }
    }

    private  RentOfferLands transformOfferLandsDTOToLands(RentOfferLandsDTO offerLandsDto) {
        RentOfferLands lands = null;
        if (offerLandsDto != null) {
            lands = new  RentOfferLands(offerLandsDto.getCadnums(), offerLandsDto.getPolygons());
        }
        return lands;
    }

    private RentOfferLandsDTO transformLandsToOfferLandsDTO(RentOfferLands lands) {
        RentOfferLandsDTO offerLandsDTOS = null;
        if (lands != null) {
            offerLandsDTOS = new RentOfferLandsDTO(lands.getCadnums(), lands.getPolygons());
        }
        return offerLandsDTOS;

    }

    private void fromCategoryAttributeDTOToOfferCategoryAttributeValue(RentOfferCategoryAttributeDTO source, RentOfferCategoryAttributeBaseValue target) {
        target.setCode(source.getCode());
        target.setTitle(source.getTitle());
        target.setUnit(source.getUnit());
        target.setType(source.getType());
    }


    public RentOfferViewShortDTO fromRentObjectToShortDTO(RentOffer rentOffer) {
        RentOfferViewShortDTO rentOfferViewShortDTO = new RentOfferViewShortDTO();
        rentOfferViewShortDTO.setTitle(rentOffer.getTitle());
        rentOfferViewShortDTO.setDescription(rentOffer.getDescription());
        return rentOfferViewShortDTO;
    }


    public RentOffer fromCreateDTOToRentObject(RentOfferCreateDTO rentObjectDTO) {
        RentOffer rentOffer = new RentOffer();
        rentOffer.setTitle(rentObjectDTO.getTitle());
        rentOffer.setDescription(rentObjectDTO.getDescription());
        rentOffer.setStatus(RentOfferStatus.NEW);
        rentOffer.setRentOfferPrice(rentOfferPriceMapper.fromDTOToModel(rentObjectDTO.getPrice()));
        return rentOffer;
    }
}