package com.amauri.algafood.infrastructure.service.report;

import com.amauri.algafood.domain.filter.VendaDiariaFilter;
import com.amauri.algafood.domain.service.VendaReportService;
import org.springframework.stereotype.Service;

@Service
public class PdfVendaReportService implements VendaReportService {


    @Override
    public byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
        return new byte[0];
    }
}
