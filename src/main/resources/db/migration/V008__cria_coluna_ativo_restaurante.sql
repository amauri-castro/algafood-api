ALTER TABLE restaurante ADD ativo TINYINT NOT NULL;
UPDATE restaurante SET ativo = 1;