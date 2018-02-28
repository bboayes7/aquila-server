
    create table additional_parties_involved (
       additional_parties_involved_id bigint not null auto_increment,
        explanation_of_involvement varchar(255),
        party_name varchar(255),
        supervisor varchar(255),
        intake_form_id bigint,
        primary key (additional_parties_involved_id)
    ) engine=MyISAM;

    create table approval_colleges (
       approval_college_id bigint not null,
        fringe_cost integer,
        meeting_expenses integer,
        personnel_cost integer,
        total_cost integer,
        form_form_id bigint,
        primary key (approval_college_id)
    ) engine=MyISAM;

    create table ApprovalForm_coPis (
       ApprovalForm_form_id bigint not null,
        co_pis varchar(255)
    ) engine=MyISAM;

    create table ApprovalForm_internalNotes (
       ApprovalForm_form_id bigint not null,
        internal_notes varchar(255)
    ) engine=MyISAM;

    create table chemicals (
       id bigint not null,
        amount varchar(255),
        chemical_name integer not null,
        primary key (id, chemical_name)
    ) engine=MyISAM;

    create table colleges (
       college_id bigint not null,
        dean tinyblob,
        college_name varchar(255),
        primary key (college_id)
    ) engine=MyISAM;

    create table conflict_of_interest_kp_non_phs (
       conflict_of_interest_non_phs_id bigint not null auto_increment,
        ari_date datetime,
        ari_official bit,
        amount_requested double precision,
        budget_period_end datetime,
        budget_period_start datetime,
        iRBACUCIBCNo bigint,
        key_personnel_date datetime,
        key_personnel_sign tinyblob,
        pi tinyblob,
        progress integer not null,
        project_period_end datetime,
        project_period_start datetime,
        proposal_number bigint not null,
        proposal_title varchar(255),
        significat_fin_interest bit,
        sponsor varchar(255),
        sub_award bit,
        subaward_agency varchar(255),
        subaward_sponsor varchar(255),
        primary key (conflict_of_interest_non_phs_id)
    ) engine=MyISAM;

    create table conflict_of_interest_kp_phs (
       conflict_of_interest_kp_phs bigint not null auto_increment,
        ari_date datetime,
        ari_official bit,
        amount_requested integer,
        budget_period_end datetime,
        budget_period_start datetime,
        iRBACUCIBCNo bigint,
        key_personnel_date datetime,
        key_personnel_sign tinyblob,
        pi tinyblob,
        progress integer not null,
        project_period_end datetime,
        project_period_start datetime,
        proposal_number bigint,
        proposal_title varchar(255),
        significant_fin_interest bit,
        primary key (conflict_of_interest_kp_phs)
    ) engine=MyISAM;

    create table conflict_of_interest_pi_non_phs (
       conflict_of_interest_pi_non_phs_id bigint not null auto_increment,
        amount_requested integer,
        ari_date datetime,
        ari_official varchar(255),
        ari_official_approved bit,
        budget_period_end datetime,
        budget_period_start datetime,
        disclosure_reason varchar(255),
        irb_iacuc_ibc_no bigint,
        other_personnel_contribution bit,
        pi_name tinyblob,
        pi_signature tinyblob,
        progress integer not null,
        project_period_end datetime,
        project_period_start datetime,
        proposal_title varchar(255),
        signature_date datetime,
        significant_financial_interest bit,
        subaward_with_federal_agency_pass_through varchar(255),
        primary key (conflict_of_interest_pi_non_phs_id)
    ) engine=MyISAM;

    create table departments (
       department_id bigint not null,
        college tinyblob,
        department_chair tinyblob,
        department_name varchar(255) not null,
        primary key (department_id)
    ) engine=MyISAM;

    create table economic_interest_pi (
       economic_interest_pi_id bigint not null auto_increment,
        actual_amount bit,
        travel_payment_amount integer,
        date_signed datetime,
        department tinyblob,
        email varchar(255),
        entity_address varchar(255),
        entity_name varchar(255),
        first_name varchar(255),
        fund_date datetime,
        funding_amount integer,
        gifts_description varchar(255),
        gifts_received bit,
        gifts_received_date datetime,
        gifts_value integer,
        invest_amount integer,
        investment_greater_than bit,
        last_name varchar(255),
        loan_amount integer,
        loan_interest double precision,
        loan_paid_off bit,
        loan_type varchar(255),
        mail_code varchar(255),
        middle_initial varchar(255),
        phone_number varchar(255),
        position_held bit,
        position_title varchar(255),
        principal_business varchar(255),
        progress integer not null,
        project_title varchar(255),
        received_amount integer,
        received_income bit,
        received_through_entity bit,
        received_through_spouse bit,
        signature varchar(255),
        statement_type varchar(255),
        travel_description varchar(255),
        travel_payment_type varchar(255),
        travel_through_entity bit,
        primary key (economic_interest_pi_id)
    ) engine=MyISAM;

    create table EquipmentForm_listOfRequirements (
       EquipmentForm_form_id bigint not null,
        list_of_requirements varchar(255)
    ) engine=MyISAM;

    create table EquipmentForm_typeOfEquipment (
       EquipmentForm_form_id bigint not null,
        type_of_equipment varchar(255)
    ) engine=MyISAM;

    create table file_info (
       file_info_id bigint not null auto_increment,
        file_name varchar(255),
        file_path varchar(255),
        file_type varchar(255),
        is_uploaded bit,
        uploader varchar(255),
        upload_date datetime,
        primary key (file_info_id)
    ) engine=MyISAM;

    create table form (
       DTYPE varchar(31) not null,
        form_id bigint not null auto_increment,
        is_complete bit,
        ari_date datetime,
        ari_official bit,
        amount_requested double precision,
        budget_period_end datetime,
        budget_period_start datetime,
        irb_iacuc_ibc_no bigint,
        pi_date datetime,
        pi_sign tinyblob,
        progress integer,
        project_period_end datetime,
        project_period_start datetime,
        proposal_title varchar(255),
        siginificant_financial_interest bit,
        FWR bit,
        air_chilled_water_flow bit,
        amps bit,
        building_location varchar(255),
        central_package_unit bit,
        circuit_breaker_specification bit,
        company_donating varchar(255),
        cost_share bit,
        dedicated_power bit,
        department varchar(255),
        director_of_facilities_services_signature tinyblob,
        director_of_facilities_services_signature_date datetime,
        director_of_research_development_signature tinyblob,
        director_of_research_development_signature_date datetime,
        donation bit,
        electrical_modification bit,
        extension bit,
        extension_value varchar(255),
        faculty_name varchar(255),
        flow_rate bit,
        fluid bit,
        fluid_temperature bit,
        fwr_paid_by varchar(255),
        hardware bit,
        hazardous_material bit,
        height integer,
        humidity_control bit,
        hvac bit,
        is_donation bit,
        length integer,
        license_requirements bit,
        maintenance bit,
        maintenance_requirement bit,
        motor_compressor_specification bit,
        new_equipment bit,
        noise_requirement bit,
        phase bit,
        plumbing bit,
        pressure bit,
        previous_use varchar(255),
        pump_compressor_motor bit,
        radiation_use varchar(255),
        room_location varchar(255),
        size_of_equipment bit,
        space_modification_requirement bit,
        special_needs bit,
        special_needs_string varchar(255),
        special_work bit,
        supply_pressure bit,
        temperature bit,
        volts bit,
        width integer,
        uas_exec_director_signature tinyblob,
        uas_exec_director_signature_date datetime,
        additional_space bit,
        additional_space_approved varchar(255),
        avp_signature tinyblob,
        avp_signature_date datetime,
        biological_hazards bit,
        biological_hazards_approved varchar(255),
        csula_cost_sharing integer,
        cfda_number integer,
        chair_signature tinyblob,
        chair_signature_date datetime,
        chief_financial_officer_signature tinyblob,
        chief_financial_officer_signature_date datetime,
        college tinyblob,
        college_dean_signature tinyblob,
        college_signature_date datetime,
        computer_equipment bit,
        computer_equipment_approved varchar(255),
        conflict_of_interest_statement bit,
        conflict_of_interest_statement_approved varchar(255),
        cost_sharing_required bit,
        deadline_date datetime,
        college_dean_designee tinyblob,
        college_dean_designee_signature datetime,
        dean_signature tinyblob,
        dean_signature_date datetime,
        dep_chair_signature tinyblob,
        dep_chair_signature_date datetime,
        director_signature tinyblob,
        director_signature_date datetime,
        email varchar(255),
        human_subjects bit,
        human_subjects_approved varchar(255),
        pi_name tinyblob,
        pi_signature tinyblob,
        pi_signature_date datetime,
        prepared_by varchar(255),
        prepared_date datetime,
        project_title varchar(255),
        proposal_code float,
        proposal_personnel_signature tinyblob,
        provost_vp_academic_affairs tinyblob,
        provost_vp_academic_affairs_signature datetime,
        project_purpose varchar(255),
        radiological_hazards bit,
        radiological_hazards_approved varchar(255),
        recombinant_dna bit,
        recombinant_dna_approved varchar(255),
        third_party_cost_share integer,
        total_csula_cost_sharing integer,
        total_of_college integer,
        total_proposal_cost_sharing integer,
        grant_contract_type varchar(255),
        proposal_type varchar(255),
        uas_project_id varchar(255),
        unrecovered_fa_cost_sharing integer,
        unrecovered_famtdc double precision,
        university_cost_sharing bit,
        vertebrate_animal bit,
        vertebrate_animal_approved varchar(255),
        agency_cost_rate_percentage integer,
        agency_cost_sharing bit,
        anticipate_stipend bit,
        archival_research bit,
        assistance_with_proposal_development bit,
        cip_category_title varchar(255),
        cip_classification varchar(255),
        coding_or_data_entry bit,
        computers_requested integer,
        data_analysis bit,
        data_collection bit,
        duplication_of_final_document_package bit,
        end_date date,
        faculty_student_research_creative_activities bit,
        lab_assistance bit,
        letter_of_support_assoc_vp_of_research bit,
        letter_of_support_president bit,
        letter_of_support_provost bit,
        literature_review bit,
        number_of_copies integer,
        number_of_graduate_students integer,
        number_of_undergrad_students integer,
        other_activities bit,
        pi_cost_sharing bit,
        presentations bit,
        pi varchar(255),
        project_summary varchar(255),
        proposed_funding_amount integer,
        questionnaire varchar(255),
        report_writing bit,
        start_date date,
        stipend varchar(255),
        students_involved bit,
        technical_assistance bit,
        vertebrate_animals bit,
        stage_id bigint,
        primary key (form_id)
    ) engine=MyISAM;

    create table hazardous_substances (
       hazardous_substances_id bigint not null,
        substance_type varchar(255),
        name_of_agent varchar(255) not null,
        primary key (hazardous_substances_id, name_of_agent)
    ) engine=MyISAM;

    create table hibernate_sequence (
       next_val bigint
    ) engine=MyISAM;

    insert into hibernate_sequence values ( 1 );

    insert into hibernate_sequence values ( 1 );

    insert into hibernate_sequence values ( 1 );

    insert into hibernate_sequence values ( 1 );

    create table investigators_names (
       investigators_names_id bigint not null,
        names_of_other_investigators varchar(255)
    ) engine=MyISAM;

    create table kp_disclosure_reasons (
       kp_disclosure_reasons_id bigint not null,
        previous_info varchar(255),
        reasons bit not null,
        primary key (kp_disclosure_reasons_id, reasons)
    ) engine=MyISAM;

    create table kp_nonphs_disclosure_reasons (
       kp_nophs_disclosure_reasons_id bigint not null,
        previous_info varchar(255),
        reasons bit not null,
        primary key (kp_nophs_disclosure_reasons_id, reasons)
    ) engine=MyISAM;

    create table kp_sponsor (
       kp_sponsor_id bigint not null,
        sponsor_name varchar(255),
        sponsor_type bit not null,
        primary key (kp_sponsor_id, sponsor_type)
    ) engine=MyISAM;

    create table other_activities (
       intake_form_id bigint not null,
        other_activity varchar(255)
    ) engine=MyISAM;

    create table personnel (
       personnel_id bigint not null auto_increment,
        employer varchar(255),
        name varchar(255),
        percent_of_time_proposed integer,
        position_title_on_grant varchar(255),
        units integer,
        intake_form_id bigint,
        primary key (personnel_id)
    ) engine=MyISAM;

    create table phs_sponsor (
       phs_sponsor_id bigint not null,
        is_sponsor varchar(255),
        sponsor_name bit not null,
        primary key (phs_sponsor_id, sponsor_name)
    ) engine=MyISAM;

    create table pi_phs_disclosure_reasons (
       pi_phs_disclosure_reasons_id bigint not null,
        previous_info varchar(255),
        reasons bit not null,
        primary key (pi_phs_disclosure_reasons_id, reasons)
    ) engine=MyISAM;

    create table project_locations (
       project_locations_id bigint not null auto_increment,
        agreement_arranged bit,
        time_on_site integer,
        site_address varchar(255),
        site_name varchar(255),
        intake_form_id bigint,
        primary key (project_locations_id)
    ) engine=MyISAM;

    create table proposal (
       proposal_id bigint not null auto_increment,
        date_created date,
        proposal_name varchar(255),
        status varchar(255),
        coi_kp_non_phs_id bigint,
        coi_kp_phs_id bigint,
        coi_phs_id bigint,
        coi_pi_non_phs_id bigint,
        economic_interest_id bigint,
        equipment_form_id bigint,
        intake_form_id bigint,
        timeline_id bigint,
        user_id bigint,
        primary key (proposal_id)
    ) engine=MyISAM;

    create table requested_equipment (
       requested_equipment_id bigint not null,
        amount integer,
        equipment_name varchar(255) not null,
        primary key (requested_equipment_id, equipment_name)
    ) engine=MyISAM;

    create table required_files (
       file_info_id bigint not null,
        timeline_id bigint not null,
        file_name varchar(255) not null,
        primary key (file_info_id, file_name)
    ) engine=MyISAM;

    create table required_forms (
       form_id bigint not null,
        timeline_id bigint not null,
        form_name varchar(255) not null,
        primary key (form_id, form_name)
    ) engine=MyISAM;

    create table sig_fin_interest_excluded (
       sig_fin_interest_excluded_id bigint not null,
        sig_fin_int_doesnt_include bit
    ) engine=MyISAM;

    create table signature (
       signature_id bigint not null,
        name varchar(255),
        primary key (signature_id)
    ) engine=MyISAM;

    create table significant_fin_interest (
       significant_fin_interest_id bigint not null,
        significant_financial_interest_reason bit
    ) engine=MyISAM;

    create table space (
       space_id bigint not null auto_increment,
        item varchar(255),
        source_of_funds varchar(255),
        type_of_use varchar(255),
        intake_form_id bigint,
        primary key (space_id)
    ) engine=MyISAM;

    create table stage (
       stage_id bigint not null auto_increment,
        comments varchar(255),
        completed_date datetime,
        deadline_type varchar(255),
        expected_date datetime,
        name varchar(255),
        uas_review_required bit,
        uas_reviewed bit,
        timeline_id bigint,
        primary key (stage_id)
    ) engine=MyISAM;

    create table subgrants_or_subcontracts (
       subgrant_or_subcontract_id bigint not null auto_increment,
        address varchar(255),
        contact_person_email varchar(255),
        contact_person_name varchar(255),
        contact_person_phone bigint,
        institution varchar(255),
        proposed_funding_amount integer,
        intake_form_id bigint,
        primary key (subgrant_or_subcontract_id)
    ) engine=MyISAM;

    create table timeline (
       timeline_id bigint not null auto_increment,
        final_sign_date datetime,
        funding_agency varchar(255),
        principal_investigator varchar(255),
        proposal varchar(255),
        sponsor_date datetime,
        uas_date datetime,
        primary key (timeline_id)
    ) engine=MyISAM;

    create table Timeline_coPI (
       Timeline_timeline_id bigint not null,
        co_pis varchar(255)
    ) engine=MyISAM;

    create table travel_payment_dates (
       tpd_id bigint not null,
        dates datetime
    ) engine=MyISAM;

    create table users (
       user_id bigint not null auto_increment,
        email varchar(255) not null,
        first_name varchar(255),
        last_name varchar(255),
        password varchar(255),
        phone_number varchar(255),
        username varchar(255) not null,
        primary key (user_id)
    ) engine=MyISAM;

    alter table conflict_of_interest_kp_non_phs 
       add constraint UK_axuy1mntk6qi21l0o05onnfo3 unique (proposal_number);

    alter table departments 
       add constraint UK_qyf2ekbfpnddm6f3rkgt39i9o unique (department_name);

    alter table required_files 
       add constraint UK_7idbrsli9ohasp39vo3qkpwmi unique (timeline_id);

    alter table required_forms 
       add constraint UK_hmpweb396ie6b8oc07mlb725p unique (timeline_id);

    alter table users 
       add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email);

    alter table users 
       add constraint UK_r43af9ap4edm43mmtq01oddj6 unique (username);

    alter table additional_parties_involved 
       add constraint FKrtaab9m7i4m49rphge66b1xs8 
       foreign key (intake_form_id) 
       references form (form_id);

    alter table approval_colleges 
       add constraint FK2doij7kd3qsdg3gabhesja1hx 
       foreign key (form_form_id) 
       references form (form_id);

    alter table ApprovalForm_coPis 
       add constraint FK83ubpey6delfeb8wao49ye7lr 
       foreign key (ApprovalForm_form_id) 
       references form (form_id);

    alter table ApprovalForm_internalNotes 
       add constraint FKsqf6gfsno2y2kdy022aijk0fd 
       foreign key (ApprovalForm_form_id) 
       references form (form_id);

    alter table chemicals 
       add constraint FK6eqxf50u8va4p06uom0ippo52 
       foreign key (id) 
       references form (form_id);

    alter table EquipmentForm_listOfRequirements 
       add constraint FK4ful3bdtw6hd0vv1a8i9bcc0l 
       foreign key (EquipmentForm_form_id) 
       references form (form_id);

    alter table EquipmentForm_typeOfEquipment 
       add constraint FKb0ql7yie14shqa01xr79hw3xe 
       foreign key (EquipmentForm_form_id) 
       references form (form_id);

    alter table form 
       add constraint FKjr3t0ti0w8f8ch6brn9pgpokc 
       foreign key (stage_id) 
       references stage (stage_id);

    alter table hazardous_substances 
       add constraint FK4igsnfxmjw3wuw8thvwbxgkir 
       foreign key (hazardous_substances_id) 
       references form (form_id);

    alter table investigators_names 
       add constraint FK3ryy8p8kan8t596q51il5coxq 
       foreign key (investigators_names_id) 
       references conflict_of_interest_pi_non_phs (conflict_of_interest_pi_non_phs_id);

    alter table kp_disclosure_reasons 
       add constraint FKq9er3dtj28de4vy1irsbx340f 
       foreign key (kp_disclosure_reasons_id) 
       references conflict_of_interest_kp_phs (conflict_of_interest_kp_phs);

    alter table kp_nonphs_disclosure_reasons 
       add constraint FKngc2wmxq3dgdj8loh6ij9p89h 
       foreign key (kp_nophs_disclosure_reasons_id) 
       references conflict_of_interest_kp_non_phs (conflict_of_interest_non_phs_id);

    alter table kp_sponsor 
       add constraint FK9bwjuy5jy3g3j085rvshnmb2f 
       foreign key (kp_sponsor_id) 
       references conflict_of_interest_kp_phs (conflict_of_interest_kp_phs);

    alter table other_activities 
       add constraint FK6jy4q546it2biinxtce13cxt7 
       foreign key (intake_form_id) 
       references form (form_id);

    alter table personnel 
       add constraint FKtkdyls5wk8hygf5ke1drcm5d 
       foreign key (intake_form_id) 
       references form (form_id);

    alter table phs_sponsor 
       add constraint FKlpx4i5tf0pyskddn6e04av4id 
       foreign key (phs_sponsor_id) 
       references form (form_id);

    alter table pi_phs_disclosure_reasons 
       add constraint FKt4879o54qd3iifta79hdqg85n 
       foreign key (pi_phs_disclosure_reasons_id) 
       references form (form_id);

    alter table project_locations 
       add constraint FK7ldg8ugry3in3gt9e4quby1y4 
       foreign key (intake_form_id) 
       references form (form_id);

    alter table proposal 
       add constraint FKr9w5phxj6oxqqy9k5g0n1n6d0 
       foreign key (coi_kp_non_phs_id) 
       references conflict_of_interest_kp_non_phs (conflict_of_interest_non_phs_id);

    alter table proposal 
       add constraint FKd6sjh3qqy7snc3ccdv1wdbe68 
       foreign key (coi_kp_phs_id) 
       references conflict_of_interest_kp_phs (conflict_of_interest_kp_phs);

    alter table proposal 
       add constraint FK448ttl86edple84c4axi90d1o 
       foreign key (coi_phs_id) 
       references form (form_id);

    alter table proposal 
       add constraint FKc54r6r702a586cvhgg8opym1l 
       foreign key (coi_pi_non_phs_id) 
       references conflict_of_interest_pi_non_phs (conflict_of_interest_pi_non_phs_id);

    alter table proposal 
       add constraint FKrgvvi6bqe7cy8oqnfih3b5l2s 
       foreign key (economic_interest_id) 
       references economic_interest_pi (economic_interest_pi_id);

    alter table proposal 
       add constraint FKs64cij1el62ay7u3q5hcoe5h1 
       foreign key (equipment_form_id) 
       references form (form_id);

    alter table proposal 
       add constraint FKcy7vdy9wo7ph6fq2jf03twat9 
       foreign key (intake_form_id) 
       references form (form_id);

    alter table proposal 
       add constraint FK1uiqx36h5gwmixwcdm62k6nqk 
       foreign key (timeline_id) 
       references timeline (timeline_id);

    alter table proposal 
       add constraint FKemv61ye7eke2swbwg3to7fmg3 
       foreign key (user_id) 
       references users (user_id);

    alter table proposal 
       add constraint FKbwvl70focwr531ksyn4h6n9pg 
       foreign key (proposal_id) 
       references users (user_id);

    alter table requested_equipment 
       add constraint FK7gr275nbngne7oyl9410liv67 
       foreign key (requested_equipment_id) 
       references form (form_id);

    alter table required_files 
       add constraint FKn6y4kmh4615rhab8cnusye8dp 
       foreign key (timeline_id) 
       references file_info (file_info_id);

    alter table required_files 
       add constraint FKc5n09xspoc82m37p2dyqkv49c 
       foreign key (file_info_id) 
       references stage (stage_id);

    alter table required_forms 
       add constraint FKrgr2cybf75mcl9ehcgsr7dg29 
       foreign key (timeline_id) 
       references form (form_id);

    alter table required_forms 
       add constraint FK2kx2uuab24l4o0ujnrnlbh6wr 
       foreign key (form_id) 
       references stage (stage_id);

    alter table sig_fin_interest_excluded 
       add constraint FKr3g38i97um9sjg0lpd8qqbxgr 
       foreign key (sig_fin_interest_excluded_id) 
       references conflict_of_interest_pi_non_phs (conflict_of_interest_pi_non_phs_id);

    alter table significant_fin_interest 
       add constraint FK4kx9tmi2yw63y6lwqhlly4r8i 
       foreign key (significant_fin_interest_id) 
       references conflict_of_interest_pi_non_phs (conflict_of_interest_pi_non_phs_id);

    alter table space 
       add constraint FKmphxnucd6y15dab1kkud99kca 
       foreign key (intake_form_id) 
       references form (form_id);

    alter table stage 
       add constraint FKsrwsf01ie8mk2fxl0yi7xgwu2 
       foreign key (timeline_id) 
       references timeline (timeline_id);

    alter table subgrants_or_subcontracts 
       add constraint FKqjtd9l0ey95ytghk4twncx2ek 
       foreign key (intake_form_id) 
       references form (form_id);

    alter table Timeline_coPI 
       add constraint FK6c981s1inlvmxia9judmtkgfu 
       foreign key (Timeline_timeline_id) 
       references timeline (timeline_id);

    alter table travel_payment_dates 
       add constraint FKvbuekp0ghddk01gso5jea9tu 
       foreign key (tpd_id) 
       references economic_interest_pi (economic_interest_pi_id);
